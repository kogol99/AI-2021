class ReadFile:
    def __init__(self, path_to_file):
        self.path_to_file = path_to_file

    def get_data(self):
        file = open(self.path_to_file, "r")
        line = file.readline().replace("\n", "")
        width, height = line.split(";")
        points_list = []
        for line in file:
            line = line.replace("\n", "")
            x1, y1, x2, y2 = line.split(";")
            points_list.append([[int(x1), int(y1)], [int(x2), int(y2)]])
        if len(points_list) == 0:
            raise ValueError
        return int(width), int(height), points_list
