public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final int TOTALNOTES = 37;
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        synthesizer.GuitarString[] stringsArray = new synthesizer.GuitarString[TOTALNOTES];
        for (int i = 0; i < TOTALNOTES; i++) {
            stringsArray[i] = new synthesizer.GuitarString(CONCERT_A
                    * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                }
                stringsArray[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < TOTALNOTES; i += 1) {
                sample +=  stringsArray[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < TOTALNOTES; i += 1) {
                stringsArray[i].tic();
            }
        }
    }
}
