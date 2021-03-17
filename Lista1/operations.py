from copy import copy
from random import randint, randrange, uniform
import sys

from PCB import PCB
from readFile import ReadFile

PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT = 0.3
PERCENTAGE_OF_THE_POPULATION_TO_ROULETTE = 0.001
PERCENTAGE_OF_CHANCES_FOR_FULL_MUTATION = 0.1
PERCENTAGE_OF_CHANCES_FOR_CROSSOVER = 0.5
PERCENTAGE_OF_CHANCES_FOR_MUTATION = 0.5
POPULATION_SIZE = 100
NUMBER_OF_REPEATIONS_THE_BEST_SCORE = 20


def tournament_operation(pcb_list):
    drawn_pcb = {}
    while len(drawn_pcb) < len(pcb_list) * PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT:
        rand = randint(0, len(pcb_list) - 1)
        drawn_pcb[rand] = pcb_list[rand].score
    return pcb_list[choose_the_best(drawn_pcb)]


def roulette_operation(pcb_list):
    drawn_pcb = {}
    total_score = 0
    # TODO improving value searches
    # Create an array with total values. When searching, divide it in half to find the value.
    # roulette_field = []
    for pcb in pcb_list:
        total_score += pcb.score
        # roulette_field.append(total_score)
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
    pcb_1 = copy(pcb_1)
    new_pcb = pcb_1
    number_of_path = len(pcb_1.path_list)
    rand = randint(0, number_of_path - 1)
    for i in range(rand, number_of_path - 1):
        new_pcb.path_list[i] = copy(pcb_2.path_list[i])
    return new_pcb


def mutation_operation(pcb, chance_for_mutation):
    new_pcb = copy(pcb)
    if uniform(0, 1) < PERCENTAGE_OF_CHANCES_FOR_FULL_MUTATION:
        new_pcb.create_random_solution()
    elif uniform(0, 1) < chance_for_mutation:
        rand = randint(0, len(new_pcb.path_list) - 1)
        new_pcb.creat_random_path_solution(new_pcb.path_list[rand])


def run_algorithm(type_of_selection="roulette"):
    t = 0
    list_of_population_list = [initialize_start_population()]
    evaluate_population(list_of_population_list[t])
    previous_the_best_score = sys.maxsize
    last_the_best_score = sys.maxsize
    number_of_repetitions_the_best_score = 0
    while number_of_repetitions_the_best_score <= NUMBER_OF_REPEATIONS_THE_BEST_SCORE:
        list_of_population_list.append([])
        while len(list_of_population_list[t+1]) != len(list_of_population_list[0]):
            if type_of_selection == "tournament":
                p1 = tournament_operation(list_of_population_list[t])
                p2 = tournament_operation(list_of_population_list[t])
            elif type_of_selection == "roulette":
                p1 = roulette_operation(list_of_population_list[t])
                p2 = roulette_operation(list_of_population_list[t])
            if uniform(0, 1) < PERCENTAGE_OF_CHANCES_FOR_CROSSOVER:
                o1 = crossover_operation(p1, p2)
            else:
                o1 = copy(p1)
            mutation_operation(o1, PERCENTAGE_OF_CHANCES_FOR_MUTATION)
            evaluate_pcb(o1)
            list_of_population_list[t+1].append(o1)
            if last_the_best_score > o1.score:
                previous_the_best_score = last_the_best_score
                last_the_best_score = o1.score
                the_best_solution = o1
                number_of_repetitions_the_best_score = 0
                print("New the best score: ", last_the_best_score)
        print("Przeszla cala populacja")
        if previous_the_best_score == last_the_best_score:
            number_of_repetitions_the_best_score += 1
            print("Repeat: ", number_of_repetitions_the_best_score)
        previous_the_best_score = last_the_best_score
        t += 1
    return the_best_solution


def initialize_start_population():
    reader = ReadFile("TestData\zad10.txt")
    width, height, points_list = reader.get_data()
    population_list = []
    for i in range(POPULATION_SIZE):
        pcb = PCB(points_list, width, height)
        pcb.assign_points()
        pcb.create_random_solution()
        population_list.append(pcb)
    return population_list


def evaluate_population(pop_list):
    for pcb in pop_list:
        pcb.create_clean_board()
        pcb.calculate_score()


def evaluate_pcb(pcb):
    pcb.create_clean_board()
    pcb.calculate_score()
