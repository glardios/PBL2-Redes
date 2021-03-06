/*
 * Esta classe fará o desenho e será inserida no JFrame da classe principal.
 */
package desenhos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.Timer;
import peertopeer.Peer;
import peertopeer.PeerThread;
import peertopeer.ServerThread;

/**
 *
 * @author root
 */
public class PainelDesenho extends JComponent implements ActionListener {

    private Carro2D carroSimples;
    private Set<Carro2D> carros = new HashSet<Carro2D>();
    private Cruzamento cruzamento;
    private int posicaoX = 0;
    private int posicaoY = 100;
    private Point posicaoNorte;
    private Point posicaoSul;
    private Point posicaoLeste;
    private Point posicaoOeste;
    private Timer t;
    private boolean controleConometro = false;
    private boolean mudarPosicao = false;
    private boolean flag = false;
    private int currentSegundo = 0;
    private int velocidade = 1000;
    private boolean timerRodando = false;
    private String posicaoOrigem;
    private String posicaoFim;

    public PainelDesenho(String nome, String porta) throws IOException {
        posicaoLeste = new Point(100, 360);
        posicaoOeste = new Point(735, 310);
        posicaoNorte = new Point(435, 0);
        posicaoSul = new Point(490, 625);
        this.cruzamento = new Cruzamento(posicaoX, posicaoY);
        this.carroSimples = new Carro2D(0, 0, "NORTE", "LESTE");
        this.carroSimples.instanciaServer(porta, nome);
        t = new Timer(10, this);
    }

    public void conectaOutroCarro(String dados) throws Exception {
        carroSimples.updateListenToPeers(dados);
        if (!carroSimples.getCarrosRodando().isEmpty()) {
            for (PeerThread peer : this.carroSimples.getCarrosRodando()) {
                if (!peer.getJsonObject().isEmpty()) {
                    String origem = peer.getJsonObject().get("posicaoInicial").toString();
                    String fim = peer.getJsonObject().get("posicaoFinal").toString();
                    Carro2D carro = new Carro2D(0, 0, origem, fim);
                    carros.add(carro);
                }
            }
        }
    }

    /**
     * Desenha o carro
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        cruzamento.draw(g);
        if (timerRodando) {
            t.start();

            this.carroSimples.paintComponent(g);
        } else {
            t.stop();
            flag = true;
            if (flag) {
                //stopTime();
            }
        }

    }

    private void desenhaCarro(Graphics g, String posicaoOrigem, String posicaoFim, int incremento) {

        if (posicaoOrigem.equalsIgnoreCase("NORTE")) {
            if (flag || carroSimples.excedeuAreaDaTela(new Point(getWidth(), getHeight()))) {
                carroSimples.setPosicao(this.posicaoNorte.getLocation());
                mudarPosicao = false;
                flag = false;
            }
            if (!mudarPosicao) {
                rodarCarro(g, incremento, 90, "SUL", currentSegundo);
            }
            if (posicaoFim.equalsIgnoreCase("LESTE") && carroSimples.getPosicao().y > 310) {
                rodarCarro(g, incremento, 0, "LESTE", currentSegundo);
                mudarPosicao = true;
            } else if (posicaoFim.equalsIgnoreCase("OESTE") && carroSimples.getPosicao().y > 355) {
                rodarCarro(g, incremento, 0, "OESTE", currentSegundo);
                mudarPosicao = true;
            }
        } else if (posicaoOrigem.equalsIgnoreCase("SUL")) {
            if (flag || carroSimples.excedeuAreaDaTela(new Point(getWidth(), getHeight() + 50))) {
                carroSimples.setPosicao(this.posicaoSul.getLocation());
                mudarPosicao = false;
                flag = false;
            }
            if (!mudarPosicao) {
                rodarCarro(g, incremento, 90, "NORTE", currentSegundo);
            }
            if (posicaoFim.equalsIgnoreCase("LESTE") && carroSimples.getPosicao().y < 310) {
                rodarCarro(g, incremento, 0, "LESTE", currentSegundo);
                mudarPosicao = true;
            } else if (posicaoFim.equalsIgnoreCase("OESTE") && carroSimples.getPosicao().y < 355) {
                rodarCarro(g, incremento, 0, "OESTE", currentSegundo);
                mudarPosicao = true;
            }
        } else if (posicaoOrigem.equalsIgnoreCase("LESTE")) {
            if (flag || carroSimples.excedeuAreaDaTela(new Point(getWidth(), getHeight() + 50))) {
                carroSimples.setPosicao(this.posicaoLeste.getLocation());
                mudarPosicao = false;
                flag = false;
            }
            if (!mudarPosicao) {
                rodarCarro(g, incremento, 0, "OESTE", currentSegundo);
            }
            if (posicaoFim.equalsIgnoreCase("NORTE") && carroSimples.getPosicao().x > 480) {
                rodarCarro(g, incremento, 90, "NORTE", currentSegundo);
                mudarPosicao = true;
            } else if (posicaoFim.equalsIgnoreCase("SUL") && carroSimples.getPosicao().x > 430) {
                rodarCarro(g, incremento, 90, "SUL", currentSegundo);
                mudarPosicao = true;
            }
        } else if (posicaoOrigem.equalsIgnoreCase("OESTE")) {
            if (flag || carroSimples.excedeuAreaDaTela(new Point(getWidth() + 70, getHeight()))) {
                carroSimples.setPosicao(this.posicaoOeste.getLocation());
                mudarPosicao = false;
                flag = false;
            }
            if (!mudarPosicao) {
                rodarCarro(g, incremento, 0, "LESTE", currentSegundo);
            }

            if (posicaoFim.equalsIgnoreCase("NORTE") && carroSimples.getPosicao().x < 490) {
                rodarCarro(g, incremento, 90, "NORTE", currentSegundo);
                mudarPosicao = true;
            } else if (posicaoFim.equalsIgnoreCase("SUL") && carroSimples.getPosicao().x < 435) {
                rodarCarro(g, incremento, 90, "SUL", currentSegundo);
                mudarPosicao = true;
            }
        }
    }

    private void rodarCarro(Graphics g, int incremento, int angulo, String direcao, int currentSegundo) {
        carroSimples.move(g, incremento, getWidth(), angulo, direcao, currentSegundo);

    }

    public boolean isTimerRodando() {
        return timerRodando;
    }

    public void setTimerRodando(boolean timerRodando) {
        this.timerRodando = timerRodando;
    }

    public void setMudarPosicao(boolean mudarPosicao) {
        this.mudarPosicao = mudarPosicao;
    }

    public String getPosicaoOrigem() {
        return posicaoOrigem;
    }

    public void setPosicaoOrigem(String posicaoOrigem) {
        this.posicaoOrigem = posicaoOrigem;
    }

    public String getPosicaoFim() {
        return posicaoFim;
    }

    public void setPosicaoFim(String posicaoFim) {
        this.posicaoFim = posicaoFim;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }
}
