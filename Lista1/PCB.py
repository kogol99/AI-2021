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
        index = -1
        for my_path in self.path_list:
            index += 1
            while my_path.actual_point != my_path.end_point:
                available_traffic_direction = []
                for chosen_direction in Direction:
                    if chosen_direction is Direction.top:
                        if (my_path.actual_point[1] - self.height + 1) * -1 > 0:
                            available_traffic_direction.append(chosen_direction)
                    elif chosen_direction is Direction.bottom:
                        if my_path.actual_point[1] > 0:
                            available_traffic_direction.append(chosen_direction)
                    elif chosen_direction is Direction.left:
                        if my_path.actual_point[0] > 0:
                            available_traffic_direction.append(chosen_direction)
                    elif chosen_direction is Direction.right:
                        if (my_path.actual_point[0] - self.width + 1) * -1 > 0:
                            available_traffic_direction.append(chosen_direction)
                my_path.add_random_segment_to_path(available_traffic_direction)

    def create_clean_board(self):
        for i_height in range(self.height):
            self.board.append([])
            for i_width in range(self.width):
                self.board[i_height].append(0)

    def recalculate_occurrence_in_pcb_points(self):
        for path_item in self.path_list:
            actual_point = copy(path_item.start_point)
            self.board[actual_point[0]][actual_point[1]] += 1
            for segment_item in path_item.segment_list:
                for i in range(segment_item.length):
                    actual_point = add_traffic_to_point(actual_point, segment_item.direction)
                    self.board[actual_point[1]][actual_point[0]] += 1

    def print_board(self):
        ##print("Number appear in each of the points")
        ##print("- x -> \t ^ y ^")
        for i_height in range(self.height, 0, -1):
            for i_width in range(self.width):
                ##print("x" + str(i_width) + " y" + str(i_height - 1) + ": "
                ##      + str(self.board[i_height - 1][i_width])
                ##      + "\t", end="")
                pass
            ##print("")

    def calculate_score(self):
        self.recalculate_occurrence_in_pcb_points()
        self.score = 0
        for path_item in self.path_list:
            path_item.calculate_intersections(self.board)
            path_item.calculate_score()
            self.score += path_item.score
        ##print("Total PCB score (less = better): " + str(self.score))

    def print_info_about_path(self):
        for path in self.path_list:
            ##print("=================== PATH ======================")
            ##print("Path start: " + str(path.start_point))
            ##print("Path end: " + str(path.end_point))
            ##print("Path length: " + str(path.length))
            ##print("Path number of intersections: " + str(path.number_of_intersections))
            ##print("Path number of segments: " + str(path.number_of_segments))
            ##print("===============================================")
            pass