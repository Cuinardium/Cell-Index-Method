import matplotlib.pyplot as plt
from matplotlib.backend_bases import Event

class ParticleVisualization:
    def __init__(
            self,
            n: int,
            l: int,
            static_values_list: list[float],
            t: list[int],
            dynamic_values_list: list[list[float]],
            neighbours: dict[int, list[int]],
            simulation_time: int
    ):
        self.n = n
        self.l = l
        self.static_values_list = static_values_list
        self.t = t
        self.dynamic_values_list = dynamic_values_list
        self.neighbours = neighbours
        self.fig, self.ax = plt.subplots()
        self.circles = []
        self.simulation_time = simulation_time

    def plot_results(self):
        self.ax.set_xlim(0, self.l)
        self.ax.set_ylim(0, self.l)
        self.ax.set_aspect('equal')

        for i in range(self.n):
            r = self.static_values_list[i]
            x, y = self.dynamic_values_list[i]
            circle = plt.Circle((x, y), r, edgecolor='r', fill=False)
            self.circles.append(circle)
            self.ax.add_artist(circle)

        self.fig.canvas.mpl_connect('button_press_event', self.on_click)
        plt.title(f'Particle Visualization (Simulation time: {self.simulation_time}ms)')
        plt.show()

    def on_click(self, event):
        for i, circle in enumerate(self.circles):
            contains, _ = circle.contains(event)
            if contains:
                self.highlight_particle(i)
                break

    def highlight_particle(self, index):
        for circle in self.circles:
            circle.set_edgecolor('r')

        self.circles[index].set_edgecolor('green')

        for neighbor_index in self.neighbours.get(index, []):
            self.circles[neighbor_index].set_edgecolor('blue')

        self.fig.canvas.draw()

