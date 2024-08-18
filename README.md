# Cell Index Method

This project is a Command Line Interface (CLI) tool that can generate particles in a square space and calculate neighboring particles using the Cell Index Method. The tool allows for both particle generation and the use of predefined particle sets.

## Build

To build the project, navigate to the project directory and use Maven:

```bash
mvn clean package
```

## Usage
To execute the program, use the following command:

```bash
java -jar cell-index-method-1.0-SNAPSHOT-jar-with-dependencies.jar [OPTIONS]
```

### Options

Short|	Long|	Argument|	Description
--- | --- | --- | ---
-h	|--help	|(none)	|Show help information and usage instructions.
-g	|--generate|	(none)|	Generate particles. If not set, input is read from the specified input directory.
-in	|--input	|\<directory\>	|Input directory. It must contain static.txt and dynamic.txt files.
-out|	--output	|\<directory\>|	Output directory where results will be stored.
-N	|--number	|\<int\>	|Number of particles to generate.
-L	|--length	|\<long\>|	Length of the area to be analyzed.
-r	|--radius	|\<double\> or \<double\>:\<double\>|	Set a constant particle radius or a random uniform radius within a specified range.
-M	|--matrix	|\<int\>	|Number of cells in the matrix (M x M).
-rc	|--cutoff	|\<double\>|	Cutoff radius for the Cell Index Method.
-t	|--toroidal	|(none)	|Use toroidal (cyclic) space.

## Input/Output File Format
### Input Files
The input directory should contain two files: static.txt and dynamic.txt.

- static.txt:
    - Line 1: Number of particles (N)
    - Line 2: Length of the area (L)
    - Following lines: Radius of each particle
- dynamic.txt:
    - Line 1: Timestamp (0)
    - Following lines: X and Y coordinates of each particle

### Output Files
The output will be saved in an output.txt file inside the specified output directory.

- output.txt:
    - Line 1: Duration of the computation in milliseconds.
    - Following lines: Particle ID followed by the IDs of its neighbors.


## Analysis Scripts
The project includes several scripts to help analyze the performance and results:

### efficiency_test.sh
- Description: Measures the time spent calculating neighbors for various values of N (number of particles) and M (number of cells).
- Usage: Run this script from the root directory of the project.

```bash
./scripts/efficiency_test.sh
```

### find_optimal_cells.py
- Description: Generates graphs to help find the optimal number of cells (M) given the area length (L) and the number of particles (N).
- Usage: Run this script from the root directory of the project.

```bash
python3 scripts/find_optimal_cells.py
```

### visualize_particles.py
- Description: Shows an interactive graph where particles are displayed in a square. Clicking on a particle highlights its neighbors.
- Usage: Can be run from any directory, but requires the directory containing the data files (static.txt, dynamic.txt, and output.txt) as an argument.

```bash
python3 scripts/graph_particles/visualize_particles.py <data_directory>
```
