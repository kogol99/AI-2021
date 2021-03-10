class Segment:
    def __init__(self, length, direction):
        self.length = length
        self.direction = direction

    def add_the_same_direction(self):
        self.length += 1
