from copy import copy

from direction import Direction
from path import Path, add_traffic_to_point


class PCB:
    def __init__(self, points_list, width, height):
        self.points_list = points_list
        self.width = width
        self.height = height
        self.score = 0
        self.path_list = []
        self.board = []

    def assign_points(self):
        for point in self.points_list:
            self.path_list.append(Path(point[0], point[1]))

    def create_random_solution(self):
        self.score = 0
        for my_path in self.path_list:
            self.creat_random_path_solution(my_path)
        self.repair_paths()

    def creat_random_path_solution(self, path, is_from_the_middle=False):
        last_good_segment = -1
        if is_from_the_middle:
            point, last_good_segment = path.find_first_intersection_point_of_the_path(self.board)
            if last_good_segment != -1:
                path.segment_list = path.segment_list[:last_good_segment + 1]
            # dodac tutaj usuwanie segmentow po tym ostatnim prawidlowym - zrobione
        if last_good_segment == -1:
            path.actual_point = copy(path.start_point)
            path.segment_list = []
        good_direction_multiply_amount = 1
        while not (path.actual_point[0] == path.end_point[0] and path.actual_point[1] == path.end_point[1]):
            available_traffic_direction = []
            for chosen_direction in Direction:
                if chosen_direction is Direction.top:
                    if (path.actual_point[1] - self.height + 1) * -1 > 0:
                        if path.end_point[1] - path.actual_point[1] > 0:
                            for i in range(good_direction_multiply_amount):
                                available_traffic_direction.append(chosen_direction)
                        available_traffic_direction.append(chosen_direction)
                elif chosen_direction is Direction.bottom:
                    if path.actual_point[1] > 0:
                        if path.actual_point[1] - path.end_point[1] > 0:
                            for i in range(good_direction_multiply_amount):
                                available_traffic_direction.append(chosen_direction)
                        available_traffic_direction.append(chosen_direction)
                elif chosen_direction is Direction.left:
                    if path.actual_point[0] > 0:
                        if path.actual_point[0] - path.end_point[0] > 0:
                            for i in range(good_direction_multiply_amount):
                                available_traffic_direction.append(chosen_direction)
                        available_traffic_direction.append(chosen_direction)
                elif chosen_direction is Direction.right:
                    if (path.actual_point[0] - self.width + 1) * -1 > 0:
                        if path.end_point[0] - path.actual_point[0] > 0:
                            for i in range(good_direction_multiply_amount):
                                available_traffic_direction.append(chosen_direction)
                        available_traffic_direction.append(chosen_direction)
            path.add_random_segment_to_path(available_traffic_direction)
        self.repair_path(path)

    def create_clean_board(self):
        self.board = []
        for i_height in range(self.height):
            self.board.append([])
            for i_width in range(self.width):
                self.board[i_height].append(0)

    def recalculate_occurrence_in_pcb_points(self):
        for path_item in self.path_list:
            actual_point = copy(path_item.start_point)
            self.board[actual_point[1]][actual_point[0]] += 1
            for segment_item in path_item.segment_list:
                for i in range(segment_item.length):
                    actual_point = add_traffic_to_point(actual_point, segment_item.direction)
                    self.board[actual_point[1]][actual_point[0]] += 1

    def print_board(self):
        print("Number appear in each of the points")
        print("- x -> \t ^ y ^")
        for i_height in range(self.height, 0, -1):
            for i_width in range(self.width):
                print("x" + str(i_width) + " y" + str(i_height - 1) + ": "
                      + str(self.board[i_height - 1][i_width])
                      + "\t", end="")
            print("")

    def calculate_score(self):
        self.recalculate_occurrence_in_pcb_points()
        self.score = 0
        for path_item in self.path_list:
            path_item.calculate_intersections(self.board)
            path_item.calculate_score()
            self.score += path_item.score
        # print("Total PCB score (less = better): " + str(self.score))

    def print_info_about_path(self):
        for path in self.path_list:
            print("=================== PATH ======================")
            print("Path start: " + str(path.start_point))
            print("Path end: " + str(path.end_point))
            print("Path length: " + str(path.length))
            print("Path number of intersections: " + str(path.number_of_intersections))
            print("Path number of segments: " + str(path.number_of_segments))
            print("===============================================")

    def print_pcb_solution(self):
        good_board = []
        for i_height in range(self.height):
            good_board.append([])
            for i_width in range(self.width):
                good_board[i_height].append(0)
        index = 1
        for i in range(len(self.path_list)):
            actual_point = copy(self.path_list[i].start_point)
            good_board[actual_point[1]][actual_point[0]] = index
            for segment_item in self.path_list[i].segment_list:
                for i in range(segment_item.length):
                    self.board[actual_point[1]][actual_point[0]] = index
                    actual_point = add_traffic_to_point(actual_point, segment_item.direction)
                self.board[actual_point[1]][actual_point[0]] = index
            index += 1
        print("Solution: ")
        print("- x -> \t ^ y ^")
        for i_height in range(self.height, 0, -1):
            for i_width in range(self.width):
                print("x" + str(i_width) + " y" + str(i_height - 1) + ": "
                      + str(self.board[i_height - 1][i_width])
                      + "\t", end="")
            print("")

    def check_intersections(self):
        intersections = 0
        for i in range(len(self.path_list)):
            intersections_number = self.path_list[i].number_of_intersections
            intersections += intersections_number
        if intersections > 0:
            # print("We have ", intersections/2, " intersections - for ", len(self.path_list), " path")
            return False
        return True

    def repair_paths(self):
        for path in self.path_list:
            self.repair_path(path)

    def repair_path(self, path):
        segment_list_to_remove = []
        i = 0
        while i < len(path.segment_list) - 1:
        # for i in range(len(path.segment_list) - 1):
            segment1 = path.segment_list[i]
            segment2 = path.segment_list[i + 1]
            if segment1.direction == Direction.top and segment2.direction == Direction.bottom:
                i += self.repair(segment1, segment2, segment_list_to_remove, i)
            elif segment1.direction == Direction.bottom and segment2.direction == Direction.top:
                i += self.repair(segment1, segment2, segment_list_to_remove, i)
            elif segment1.direction == Direction.left and segment2.direction == Direction.right:
                i += self.repair(segment1, segment2, segment_list_to_remove, i)
            elif segment1.direction == Direction.right and segment2.direction == Direction.left:
                i += self.repair(segment1, segment2, segment_list_to_remove, i)
            i += 1
        for i in range(len(segment_list_to_remove), 0, -1):
            del path.segment_list[segment_list_to_remove[i - 1]]
            # path.segment_list.remove(segment_list_to_remove[i - 1])

    def repair(self, segment1, segment2, segment_list_to_remove, i):
        difference = segment1.length - segment2.length
        if difference > 0:
            segment1.length = difference
            segment_list_to_remove.append(i + 1)
            return 1
        elif difference < 0:
            segment_list_to_remove.append(i)
            segment2.length = difference * -1
            return 0
        elif difference == 0:
            segment_list_to_remove.append(i)
            segment_list_to_remove.append(i + 1)
            return 1
