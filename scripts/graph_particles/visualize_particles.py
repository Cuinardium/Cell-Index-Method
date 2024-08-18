import visualization_utils as vu
import parse_utils as pu
import sys

STATIC_FILENAME = 'static.txt'
DYNAMIC_FILENAME = 'dynamic.txt'
OUTPUT_FILENAME = 'output.txt'

if __name__ == '__main__':

    # arg[1] is the path to the data files
    args = sys.argv
    if len(args) != 2:
        print('Usage: python visualize_particles.py <path_to_data_files>')
        sys.exit(1)

    DATA_PATH = args[1]

    n, l, static_values = pu.parse_static(DATA_PATH + '/' + STATIC_FILENAME)
    t, dynamic_values = pu.parse_dynamic(DATA_PATH + '/' + DYNAMIC_FILENAME)
    simulation_time, neighbours = pu.parse_output(DATA_PATH + '/' + OUTPUT_FILENAME)

    vis = vu.ParticleVisualization(n, l, static_values, t, dynamic_values, neighbours, simulation_time)

    vis.plot_results()
