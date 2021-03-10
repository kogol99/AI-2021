from direction import Direction
from path import Path


class PCB:
    def __init__(self, points_list, width, height):
        self.points_list = points_list
        self.width = width
        self.height = height
        self.score = None
        self.path_list = []

    def assign_points(self):
        for point in self.points_list:
            self.path_list.append(Path(point[0], point[1]))

    def create_random_solution(self):
        for my_path in self.path_list:
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
