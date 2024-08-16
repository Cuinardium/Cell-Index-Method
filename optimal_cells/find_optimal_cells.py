import math
import os

def calculate_optimal_M(L, r_c, r_max, N):
    # Ensure that L/M > r_c + r
    M_rc = math.floor(L / (r_c + r)) - 1

    if(L/M_rc == r_c +r):
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
    print(f'Simulation time for M={M}:')
    os.system(f'java -jar ../target/cell-index-method-1.0-SNAPSHOT-jar-with-dependencies.jar {g} -out {data_path} -N {N} -M {M} -L {L} -rc {rc} -r {r}')

    with open(f'{data_path}/output.txt', 'r') as f:
        lines = f.readlines()

    return int(lines[0])


L = 20
rc = 1
r = 0.25
N = 1000
M = calculate_optimal_M(L, rc, r, N)

print(f'Optimal M: {M}')
simulation_time = execute_simulation(M, L, rc, r, N)

faster_simulation_time = simulation_time
M_faster = M

print('Simulation times for smaller Ms:')
for M_star_1 in range(M - 1, 0, -1):
    simulation_time = execute_simulation(M_star_1, L, rc, r, N, '-in data')
    if simulation_time < faster_simulation_time:
        faster_simulation_time = simulation_time
        M_faster = M_star_1

print('Simulation times for larger Ms:')
for M_star_2 in range(M + 1, math.floor(L / rc)):
    simulation_time = execute_simulation(M_star_2, L, rc, r, N, '-in data')
    if simulation_time < faster_simulation_time:
        faster_simulation_time = simulation_time
        M_faster = M_star_2

print(f'Faster simulation time: {faster_simulation_time} for M={M_faster}')

if M_faster == M:
    print('The optimal M is the same as the faster M')
else:
    print('The optimal M is not the same as the faster M')



