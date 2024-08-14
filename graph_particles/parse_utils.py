STATIC_DYNAMIC_DELIMITER = '   '
OUTPUT_DELIMITER = ','
def parse_static(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    static_values = []
    n, l = 0, 0
    for i, line in enumerate(lines):
        if i == 0:
            n = int(line)
        elif i == 1:
            l = int(line)
        else:
            line_values = line.strip().split(STATIC_DYNAMIC_DELIMITER)
            static_values.append([float(v) for v in line_values])

    return n, l, static_values

def parse_dynamic(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    dynamic_values = []
    t = 0
    for i, line in enumerate(lines):
        if i == 0:
            t = int(line)
        else:
            line_values = line.strip().split(STATIC_DYNAMIC_DELIMITER)
            dynamic_values.append([float(v) for v in line_values])

    return t, dynamic_values


def parse_neighbours(path):
    with open(path, 'r') as f:
        lines = f.readlines()

    neighbours = {}
    for line in lines:
        line_values = line.strip().split(OUTPUT_DELIMITER)
        neighbours[int(line_values[0])] = [int(v) for v in line_values[1:]]

    return neighbours