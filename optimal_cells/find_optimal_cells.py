import math
import os
import matplotlib.pyplot as plt

def calculate_optimal_M(L, r_c, r_max, N):
    # Ensure that L/M > r_c + r_max
    M_rc = math.floor(L / (r_c + 2 * r_max))

    if(L/M_rc <= r_c + 2 * r_max):
        M_rc -= 1

    # Calculate particle density
    density = N / (L ** 2)

    # Calculate M based on density and particle size
    M_density = math.sqrt(density * (L ** 2) / ((r_c + 2 * r_max) ** 2))

    # The optimal M should be the minimum of these values
    M_optimal = min(M_rc, M_density)

    if M_optimal < 1:
        return 1

    return int(M_optimal)

def execute_simulation(M, L, rc, r, N, g='-g'):
    data_path = 'data'
    os.system(f'java -jar ../target/cell-index-method-1.0-SNAPSHOT-jar-with-dependencies.jar {g} -out {data_path} -N {N} -M {M} -L {L} -rc {rc} -r {r}')

    with open(f'{data_path}/output.txt', 'r') as f:
        lines = f.readlines()

    return int(lines[0])



N_to_test = [100, 1000, 10000, 20000]
num_simulations = 10

for N in N_to_test:
    all_simulation_times = []
    L = 20
    rc = 1
    r = 0.25
    M = calculate_optimal_M(L, rc, r, N)
    for i in range(num_simulations):
        simulation_time = execute_simulation(M, L, rc, r, N)

        simulation_times = {M: simulation_time}
        for M_star_1 in range(M - 1, 0, -1):
            simulation_time = execute_simulation(M_star_1, L, rc, r, N, '-in data')
            simulation_times[M_star_1] = simulation_time

        for M_star_2 in range(M + 1, math.floor((L / (rc + 2 * r)))):
            simulation_time = execute_simulation(M_star_2, L, rc, r, N, '-in data')
            simulation_times[M_star_2] = simulation_time

        all_simulation_times.append(simulation_times)

    avg_simulation_times = {}
    for simulation_times in all_simulation_times:
        for M_star, time in simulation_times.items():
            if M_star in avg_simulation_times:
                avg_simulation_times[M_star] += time
            else:
                avg_simulation_times[M_star] = time

    for M_star in avg_simulation_times.keys():
        avg_simulation_times[M_star] /= num_simulations

    M_faster = min(avg_simulation_times, key=avg_simulation_times.get)

    colors = ['blue' if m != M and m != M_faster else 'red' if m == M else 'green' for m in avg_simulation_times.keys()]
    plt.bar(list(avg_simulation_times.keys()), list(avg_simulation_times.values()), color=colors)
    plt.xlabel('M')
    plt.ylabel('Simulation time (ms)')
    plt.title(f'Simulation Times for N = {N}')
    plt.show()






