DELIMITER = ' '
N_INDEX = 0
L_INDEX = 1
T_INDEX = 0
SIMULATION_TIME_INDEX = 0

def parse_static(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    static_values = []
    n, l = 0, 0
    for i, line in enumerate(lines):
        if i == N_INDEX:
            n = int(line)
        elif i == L_INDEX:
            l = int(line)
        else:
            # If there is more than one value per line, use this instead
            # line_values = line.strip().split(DELIMITER)
            # static_values.append(float(v) for v in line_values])
            static_values.append(float(line))

    return n, l, static_values

def parse_dynamic(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    dynamic_values = []
    t = 0
    for i, line in enumerate(lines):
        if i == T_INDEX:
            t = int(line)
        else:
            line_values = line.strip().split(DELIMITER)
            dynamic_values.append([float(v) for v in line_values])

    return t, dynamic_values


def parse_output(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    neighbours = {}
    simulation_time = 0
    for i, line in enumerate(lines):
        if i == SIMULATION_TIME_INDEX:
            simulation_time = int(line)
        else:
            line_values = line.strip().split(DELIMITER)
            neighbours[int(line_values[0])] = [int(v) for v in line_values[1:]]

    return simulation_time, neighbours