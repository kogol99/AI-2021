from PCB import PCB
from readFile import ReadFile

if __name__ == '__main__':
    reader = ReadFile("TestData\zad3.txt")
    width, height, points_list = reader.get_data()
    test = PCB(points_list, width, height)
    test.assign_points()
    test.create_random_solution()
