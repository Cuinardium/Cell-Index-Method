package ar.edu.itba.ss.g2.args;

public class Configuration {

    private String inputDirectory;
    private String outputDirectory;

    private boolean generate;

    private int N;
    private long L;

    private boolean constantRadius;
    private double r;
    private double minR;
    private double maxR;

    private long M;
    private double rc;

    private Configuration(Builder builder) {
        this.inputDirectory = builder.inputDirectory;
        this.outputDirectory = builder.outputDirectory;
        this.generate = builder.generate;
        this.N = builder.N;
        this.L = builder.L;
        this.constantRadius = builder.constantRadius;
        this.r = builder.r;
        this.minR = builder.minR;
        this.maxR = builder.maxR;
        this.M = builder.M;
        this.rc = builder.rc;
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public boolean generate() {
        return generate;
    }

    public int getN() {
        return N;
    }

    public long getL() {
        return L;
    }

    public boolean isConstantRadius() {
        return constantRadius;
    }

    public double getR() {
        return r;
    }

    public double getMinR() {
        return minR;
    }

    public double getMaxR() {
        return maxR;
    }

    public long getM() {
        return M;
    }

    public double getRc() {
        return rc;
    }

    public static class Builder {

        private String inputDirectory;
        private String outputDirectory;

        private boolean generate = false;

        private int N;
        private long L;

        private boolean constantRadius = false;
        private double r;
        private double minR;
        private double maxR;

        private long M;
        private double rc;

        public Builder() {}

        public Builder inputDirectory(String inputDirectory) {
            this.inputDirectory = inputDirectory;
            return this;
        }

        public Builder outputDirectory(String outputDirectory) {
            this.outputDirectory = outputDirectory;
            return this;
        }


        public Builder generate() {
            this.generate = true;
            return this;
        }

        public Builder readInputDirectory(String inputDirectory) {
            this.generate = false;
            this.inputDirectory = inputDirectory;
            return this;
        }

        public Builder N(int N) {
            this.N = N;
            return this;
        }

        public Builder L(long L) {
            this.L = L;
            return this;
        }

        public Builder constantRadius(double r) {
            this.constantRadius = true;
            this.r = r;
            return this;
        }

        public Builder randomRadius(double minR, double maxR) {
            this.constantRadius = false;
            this.minR = minR;
            this.maxR = maxR;
            return this;
        }

        public Builder M(long M) {
            this.M = M;
            return this;
        }

        public Builder rc(double rc) {
            this.rc = rc;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
