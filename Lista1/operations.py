from random import randint
import sys

PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT = 0.3
PERCENTAGE_OF_THE_POPULATION_TO_ROULETTE = 0.3


def tournament_operation(pcb_list):
    drawn_pcb = {}
    while len(drawn_pcb) != len(pcb_list) * PERCENTAGE_OF_THE_POPULATION_TO_TOURNAMENT:
        rand = randint(0, len(pcb_list) - 1)
        drawn_pcb[rand] = pcb_list[rand].score
    return choose_the_best(drawn_pcb)


def roulette_operation(pcb_list):
    drawn_pcb = {}
    total_score = 0
    # TODO improving value searches
    # Create an array with total values. When searching, divide it in half to find the value.
    # roulette_field = []
    for pcb in pcb_list:
        total_score += pcb.score
        # roulette_field.append(total_score)
    while len(drawn_pcb) != len(pcb_list) * PERCENTAGE_OF_THE_POPULATION_TO_ROULETTE:
        rand = randint(1, int(total_score))
        total_score_search = 0
        for i in range(len(pcb_list)):
            actual_pcb_score = pcb_list[i].score
            total_score_search += actual_pcb_score
            if total_score_search > rand:
                drawn_pcb[i - 1] = actual_pcb_score
                break
    return choose_the_best(drawn_pcb)


def choose_the_best(pcb_list):
    the_best_score = sys.maxsize
    the_best_score_index = 0
    for index, score in pcb_list.items():
        if score < the_best_score:
            the_best_score = score
            the_best_score_index = index
    return pcb_list[the_best_score_index]
