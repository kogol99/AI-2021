from copy import copy, deepcopy
from random import randint, uniform
import sys

from PCB import PCB
from readFile import ReadFile

PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT = 0.75
PERCENTAGE_OF_THE_POPULATION_TO_ROULETTE = 0.001
PERCENTAGE_OF_CHANCES_FOR_FULL_MUTATION = 0.001
PERCENTAGE_OF_CHANCES_FOR_CROSSOVER = 0.15
PERCENTAGE_OF_CHANCES_FOR_MUTATION = 0.6
PERCENTAGE_OF_CHANCES_FOR_MIDDLE_MUTATION = 0.5
POPULATION_SIZE = 150
NUMBER_OF_REPEATIONS_THE_BEST_SCORE = 100
MAX_POPULATION_AMOUNT = 200


def tournament_operation(pcb_list):
    drawn_pcb = {}
    while len(drawn_pcb) < len(pcb_list) * PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT:
        rand = randint(0, len(pcb_list) - 1)
        drawn_pcb[rand] = pcb_list[rand].score
    return pcb_list[choose_the_best(drawn_pcb)]


def roulette_operation(pcb_list):
    drawn_pcb = {}
    total_score = 0
    for pcb in pcb_list:
        total_score += pcb.score
    total_reverse_score = 0
    for pcb in pcb_list:
        total_reverse_score += total_score - pcb.score
    while len(drawn_pcb) < len(pcb_list) * PERCENTAGE_OF_THE_POPULATION_TO_ROULETTE:
        rand = randint(1, int(total_reverse_score))
        total_score_search = 0
        for i in range(len(pcb_list)):
            total_score_search += total_score - pcb_list[i].score
            if total_score_search > rand:
                drawn_pcb[i] = pcb_list[i].score
                break
    return pcb_list[choose_the_best(drawn_pcb)]


def choose_the_best(pcb_list):
    the_best_score = sys.maxsize
    the_best_score_index = 0
    for index, score in pcb_list.items():
        if score < the_best_score:
            the_best_score = score
            the_best_score_index = index
    return the_best_score_index


def crossover_operation(pcb_1, pcb_2):
    new_pcb = PCB(copy(pcb_1.points_list), copy(pcb_1.width), copy(pcb_1.height))
    number_of_path = len(pcb_1.path_list)
    rand = randint(0, number_of_path - 1)
    for i in range(0, rand):
        new_pcb.path_list.append(deepcopy(pcb_1.path_list[i]))
    for i in range(rand, number_of_path):
        new_pcb.path_list.append(deepcopy(pcb_2.path_list[i]))
    return new_pcb


def mutation_operation(pcb, chance_for_mutation):
    if uniform(0, 1) < PERCENTAGE_OF_CHANCES_FOR_FULL_MUTATION:
        pcb.create_random_solution()
    elif uniform(0, 1) < chance_for_mutation:
        rand = randint(0, len(pcb.path_list) - 1)
        if uniform(0, 1) < PERCENTAGE_OF_CHANCES_FOR_MIDDLE_MUTATION:
            middle = True
        else:
            middle = False
        pcb.creat_random_path_solution(pcb.path_list[rand], middle)


def run_algorithm(type_of_selection="roulette"):
    min_list = []
    max_list = []
    avg_list = []
    count_list = []
    t = 0
    list_of_population_list = [initialize_start_population()]
    evaluate_population(list_of_population_list[t])
    previous_the_best_score = sys.maxsize
    last_the_best_score = sys.maxsize
    number_of_repetitions_the_best_score = 0
    len_of_list_of_population = len(list_of_population_list[0])
    there_are_intersections = False
    number_of_population = 1
    # while number_of_repetitions_the_best_score <= NUMBER_OF_REPEATIONS_THE_BEST_SCORE: # or not there_are_intersections:
    while number_of_population < MAX_POPULATION_AMOUNT + 1:
        list_of_population_list.append([])
        while len(list_of_population_list[t+1]) != len_of_list_of_population:
            if type_of_selection == "tournament":
                p1 = tournament_operation(list_of_population_list[t])
                p2 = tournament_operation(list_of_population_list[t])
            elif type_of_selection == "roulette":
                p1 = roulette_operation(list_of_population_list[t])
                p2 = roulette_operation(list_of_population_list[t])
            if uniform(0, 1) < PERCENTAGE_OF_CHANCES_FOR_CROSSOVER:
                o1 = crossover_operation(p1, p2)
            else:
                o1 = deepcopy(p1)
            evaluate_pcb(o1)
            mutation_operation(o1, PERCENTAGE_OF_CHANCES_FOR_MUTATION)
            evaluate_pcb(o1)
            list_of_population_list[t+1].append(o1)
            if last_the_best_score > o1.score:
                previous_the_best_score = last_the_best_score
                last_the_best_score = o1.score
                the_best_solution = o1
                number_of_repetitions_the_best_score = 0
                # print("New the best score: ", last_the_best_score)
        # print(str(number_of_population) + " - The entire population has passed")
        if previous_the_best_score == last_the_best_score:
            number_of_repetitions_the_best_score += 1
            # print("Repeat: ", number_of_repetitions_the_best_score)
        previous_the_best_score = last_the_best_score
        the_best_solution.check_intersections()
        list_of_population_list.pop(t)
        min_list.append(search_min(list_of_population_list[0]))
        max_list.append(search_max(list_of_population_list[0]))
        avg_list.append(calc_avg(list_of_population_list[0]))
        count_list.append(number_of_population)
        number_of_population += 1
    return the_best_solution, min_list, max_list, avg_list, count_list


def search_max(list_of_population):
    max_value = 0
    for item in list_of_population:
        if item.score > max_value:
            max_value = item.score
    return max_value


def search_min(list_of_population):
    min_value = sys.maxsize
    for item in list_of_population:
        if item.score < min_value:
            min_value = item.score
    return min_value


def calc_avg(list_of_population):
    amount = len(list_of_population)
    sum_value = 0
    for item in list_of_population:
        sum_value += item.score
    return sum_value/amount


def initialize_start_population():
    reader = ReadFile("TestData\zad1.txt")
    width, height, points_list = reader.get_data()
    population_list = []
    for i in range(POPULATION_SIZE):
        pcb = PCB(points_list, width, height)
        pcb.assign_points()
        pcb.create_random_solution()
        pcb.repair_paths()
        population_list.append(pcb)
    return population_list


def evaluate_population(pop_list):
    for pcb in pop_list:
        pcb.create_clean_board()
        pcb.calculate_score()


def evaluate_pcb(pcb):
    pcb.create_clean_board()
    pcb.calculate_score()
