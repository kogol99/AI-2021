from PCB import PCB
from operations import tournament_operation, roulette_operation
from readFile import ReadFile

POPULATION_SIZE = 50

if __name__ == '__main__':
    reader = ReadFile("TestData\zad3.txt")
    width, height, points_list = reader.get_data()
    population_list = []
    for i in range(POPULATION_SIZE):
        test = PCB(points_list, width, height)
        test.assign_points()
        test.create_random_solution()
        test.create_clean_board()
        test.calculate_score()
        population_list.append(test)

    print(tournament_operation(population_list))
    print(roulette_operation(population_list))
    # test = PCB([[[0, 0], [2, 2]]], 3, 3)
    # test.print_info_about_path()
    # test.print_board()

