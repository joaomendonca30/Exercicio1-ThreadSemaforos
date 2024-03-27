package controller;

import java.util.concurrent.Semaphore;

public class CavaleiroController extends Thread{

    private static final int DISTANCIA_TOTAL = 2000;
    private static final int TOCHA_POSICAO = 500;
    private static final int PEDRA_POSICAO = 1500;

    private static Semaphore semaforoTocha = new Semaphore(1);
    private static Semaphore semaforoPedra = new Semaphore(1);
    private static Semaphore semaforoPortas = new Semaphore(1);

    private static int distanciaPercorrida = 0;
    private int velocidade;

    public CavaleiroController(String nome, int velocidade) {
        super(nome);
        this.velocidade = velocidade;
    }

    private void caminhar() throws InterruptedException {
        Thread.sleep(50);
        distanciaPercorrida += velocidade;
        System.out.println("O "+getName() + " percorreu " + velocidade + " m. Distancia total: " + distanciaPercorrida + " m.");
    }

    private void pegarTocha() throws InterruptedException {
        if (distanciaPercorrida >= TOCHA_POSICAO && distanciaPercorrida < TOCHA_POSICAO + velocidade) {
            semaforoTocha.acquire();
            System.out.println("O "+getName() + " pegou a tocha.");
            velocidade += 2;
        }
    }

    private void pegarPedra() throws InterruptedException {
        if (distanciaPercorrida >= PEDRA_POSICAO && distanciaPercorrida < PEDRA_POSICAO + velocidade) {
            semaforoPedra.acquire();
            System.out.println("O "+getName() + " pegou a pedra.");
            velocidade += 2;
        }
    }

    private void escolherPorta() throws InterruptedException {
        semaforoPortas.acquire();
        int portaEscolhida = (int) (Math.random() * 4) + 1;
        System.out.println(getName() + " escolheu a porta " + portaEscolhida);
        semaforoPortas.release();
    }

    @Override
    public void run() {
        try {
            while (distanciaPercorrida < DISTANCIA_TOTAL) {
                caminhar();
                pegarTocha();
                pegarPedra();
            }
            escolherPorta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
