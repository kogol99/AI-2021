from random import choice
from segment import Segment


class Path:
    def __init__(self, start_point, end_point):
        self.start_point = start_point
        self.end_point = end_point
        self.actual_point = self.start_point
        self.segment_list = []

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
                self.add_traffic_to_actual_point(direction)
        else:
            self.segment_list.append(Segment(1, direction))
            self.add_traffic_to_actual_point(direction)
        print()

    def add_traffic_to_actual_point(self, direction):
        if direction is direction.top:
            self.actual_point[1] += 1
        elif direction is direction.bottom:
            self.actual_point[1] -= 1
        elif direction is direction.left:
            self.actual_point[0] -= 1
        elif direction is direction.right:
            self.actual_point[0] += 1