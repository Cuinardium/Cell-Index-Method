import visualization_utils as vu
import parse_utils as pu

DATA_PATH = '../data/'
STATIC_FILENAME = 'static.txt'
DYNAMIC_FILENAME = 'dynamic.txt'
OUTPUT_FILENAME = 'output.txt'

if __name__ == '__main__':
    n, l, static_values = pu.parse_static(DATA_PATH + STATIC_FILENAME)
    t, dynamic_values = pu.parse_dynamic(DATA_PATH + DYNAMIC_FILENAME)
    simulation_time, neighbours = pu.parse_output(DATA_PATH + OUTPUT_FILENAME)

    vis = vu.ParticleVisualization(n, l, static_values, t, dynamic_values, neighbours, simulation_time)

    vis.plot_results()