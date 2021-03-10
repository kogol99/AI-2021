from copy import copy
from random import choice
from segment import Segment

INTERSECTIONS_WEIGHT = 2
LENGTH_WEIGHT = 0.3
SEGMENT_WEIGHT = 0.5


def add_traffic_to_point(point, direction):
    if direction is direction.top:
        point[1] += 1
    elif direction is direction.bottom:
        point[1] -= 1
    elif direction is direction.left:
        point[0] -= 1
    elif direction is direction.right:
        point[0] += 1
    return point


class Path:
    def __init__(self, start_point, end_point):
        self.start_point = start_point
        self.end_point = end_point
        self.actual_point = copy(self.start_point)
        self.segment_list = []
        self.length = 0
        self.number_of_segments = 0
        self.number_of_intersections = 0
        self.score = 0

    def add_random_segment_to_path(self, available_direction):
        self.add_step(choice(available_direction))

    def add_step(self, direction):
        if len(self.segment_list) > 0:
            last_segment_list_object = self.segment_list[len(self.segment_list) - 1]
            if last_segment_list_object.direction == direction:
                last_segment_list_object.add_the_same_direction()
                self.add_traffic_to_actual_point(direction)
            else:
                self.segment_list.append(Segment(1, direction))
                self.number_of_segments += 1
                self.add_traffic_to_actual_point(direction)
        else:
            self.segment_list.append(Segment(1, direction))
            self.number_of_segments += 1
            self.add_traffic_to_actual_point(direction)
        self.length += 1

    def add_traffic_to_actual_point(self, direction):
        if direction is direction.top:
            add_traffic_to_point(self.actual_point, direction)
        elif direction is direction.bottom:
            add_traffic_to_point(self.actual_point, direction)
        elif direction is direction.left:
            add_traffic_to_point(self.actual_point, direction)
        elif direction is direction.right:
            add_traffic_to_point(self.actual_point, direction)

    def calculate_intersections(self, board):
        actual_point = copy(self.start_point)
        for segment_item in self.segment_list:
            for i in range(segment_item.length):
                actual_point = add_traffic_to_point(actual_point, segment_item.direction)
                if board[actual_point[1]][actual_point[0]] > 1:
                    self.number_of_intersections += 1

    def calculate_score(self):
        self.score = INTERSECTIONS_WEIGHT * self.number_of_intersections + LENGTH_WEIGHT * self.length + \
                      SEGMENT_WEIGHT * self.number_of_segments
