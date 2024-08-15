import visualization_utils as vu
import parse_utils as pu

if __name__ == '__main__':
    # Parse the static and dynamic values
    n, l, static_values = pu.parse_static('ArchivosEjemplo/static.txt')
    t, dynamic_values = pu.parse_dynamic('ArchivosEjemplo/dynamic.txt')

    # Parse the neighbours
    simulation_time, neighbours = pu.parse_output('ArchivosEjemplo/output.txt')

    # Create a visualization object
    vis = vu.ParticleVisualization(n, l, static_values, t, dynamic_values, neighbours, simulation_time)

    # Plot the results
    vis.plot_results()