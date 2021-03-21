from PCB import PCB
from operations import run_algorithm, mutation_operation, tournament_operation, roulette_operation, crossover_operation
from readFile import ReadFile

POPULATION_SIZE = 5

if __name__ == '__main__':
    """reader = ReadFile("TestData\zad3.txt")
    width, height, points_list = reader.get_data()
    population_list = []
    for i in range(POPULATION_SIZE):
        test = PCB(points_list, width, height)
        test.assign_points()
        test.create_random_solution()
        test.create_clean_board()
        test.calculate_score()
        mutation_operation(test, 0.3)
        test.create_clean_board()
        test.calculate_score()
        population_list.append(test)

    print(tournament_operation(population_list).score)
    print(roulette_operation(population_list).score)

    crossover_operation(population_list[0], population_list[1])"""
    #
    # test.print_info_about_path()
    # test.print_board()
    the_best = run_algorithm("roulette")
    print(the_best.score)
    the_best.print_board()
    the_best.print_pcb_solution()
