package view;

import controller.CavaleiroController;

public class Main {
    public static void main(String[] args) {
        CavaleiroController[] cavaleiro = new CavaleiroController[4];
        cavaleiro[0] = new CavaleiroController("Cavaleiro 1", 2);
        cavaleiro[1] = new CavaleiroController("Cavaleiro 2", 3);
        cavaleiro[2] = new CavaleiroController("Cavaleiro 3", 4);
        cavaleiro[3] = new CavaleiroController("Cavaleiro 4", 4);

        for (CavaleiroController cavaleiros : cavaleiro) {
            cavaleiros.start();
        }

        for (CavaleiroController cavaleiros : cavaleiro) {
            try {
                cavaleiros.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}