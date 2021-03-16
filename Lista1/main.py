from PCB import PCB
from readFile import ReadFile

if __name__ == '__main__':
    reader = ReadFile("TestData\zad3.txt")
    width, height, points_list = reader.get_data()
    test = PCB(points_list, width, height)
    #test = PCB([[[0, 0], [2, 2]]], 3, 3)
    test.assign_points()
    test.create_random_solution()
    test.create_clean_board()
    test.calculate_score()
    test.print_info_about_path()
    test.print_board()

