from PCB import PCB


if __name__ == '__main__':
    new = PCB([[[0, 0], [2, 2]]], 3, 3)
    new.assign_points()
    new.create_random_solution()
    print(new)