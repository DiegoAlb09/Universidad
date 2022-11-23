
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class CheemsPhotoShop extends JFrame {
    private JMenuBar arc;
    private JMenu uno, dos, tres, cuatro, cinco, seis, siete, ocho;
    private JMenuItem u1, u2, u3, u4, d1, d2, d3, d4, d5, e1, e2, e3, e4,
            r1, r2, r3, r4, x1, x2, s1, s2, rgb1, rgb2, rgb3, a1;
    private Container contents;
    private JLabel imagen;
    private BufferedImage buff;

    // Archivos
    FileInputStream entrada;
    FileOutputStream salida;
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    byte[] bytesImg;

    public CheemsPhotoShop() {
        // Crear la ventana
        super("Cheems PhotoShop");
        setIconImage(new ImageIcon(getClass().getResource("Cheems.jpg")).getImage());
        contents = getContentPane();
        contents.setLayout(new BorderLayout());

        // Crear el objeto de la imagen
        imagen = new JLabel();
        imagen.setHorizontalAlignment(JLabel.CENTER);
        imagen.setVerticalAlignment(JLabel.CENTER);
        PHandler alter = new PHandler();

        // Crear la barra de menus
        arc = new JMenuBar();
        uno = new JMenu("Archivo");
        u1 = new JMenuItem("Abrir");
        u1.addActionListener(alter);
        u2 = new JMenuItem("Original");
        u2.addActionListener(alter);
        u3 = new JMenuItem("Guardar como");
        u3.addActionListener(alter);
        u4 = new JMenuItem("Salir");
        u4.addActionListener(alter);

        dos = new JMenu("Pre-procesamiento");
        d1 = new JMenuItem("Negativo");
        d1.addActionListener(alter);
        d2 = new JMenuItem("Grises");
        d2.addActionListener(alter);
        d3 = new JMenuItem("Brillantar");
        d3.addActionListener(alter);
        d4 = new JMenuItem("Oscurecer");
        d4.addActionListener(alter);
        d5 = new JMenuItem("Binarizacion");
        d5.addActionListener(alter);

        tres = new JMenu("Bordes");
        e1 = new JMenuItem("Laplace");
        e1.addActionListener(alter);
        e2 = new JMenuItem("Sobel");
        e2.addActionListener(alter);
        e3 = new JMenuItem("Roberts");
        e3.addActionListener(alter);
        e4 = new JMenuItem("Prewit");
        e4.addActionListener(alter);

        cuatro = new JMenu("Rotar Imagen");
        r1 = new JMenuItem("Rotar a la derecha");
        r1.addActionListener(alter);
        r2 = new JMenuItem("Rotar a la izquierda");
        r2.addActionListener(alter);
        r3 = new JMenuItem("Rotar por angulo");
        r3.addActionListener(alter);
        r4 = new JMenuItem("Espejo");
        r4.addActionListener(alter);

        cinco = new JMenu("Tamaño");
        x1 = new JMenuItem("Mas Pequeña");
        x1.addActionListener(alter);
        x2 = new JMenuItem("Mas Grande");
        x2.addActionListener(alter);

        seis = new JMenu("Efectos");
        s1 = new JMenuItem("Desenfoque");
        s1.addActionListener(alter);
        s2 = new JMenuItem("Mosaico");
        s2.addActionListener(alter);

        siete = new JMenu("RGB");
        rgb1 = new JMenuItem("Rojo");
        rgb1.addActionListener(alter);
        rgb2 = new JMenuItem("Verde");
        rgb2.addActionListener(alter);
        rgb3 = new JMenuItem("Azul");
        rgb3.addActionListener(alter);

        ocho = new JMenu("Ayuda");
        a1 = new JMenuItem("Acerca de");
        a1.addActionListener(alter);

        // Agregar los menus a la barra
        contents.add(arc, BorderLayout.NORTH);
        arc.add(uno);
        uno.add(u1);
        uno.add(u2);
        uno.add(u3);
        uno.add(u4);

        arc.add(dos);
        dos.add(d1);
        dos.add(d2);
        dos.add(d3);
        dos.add(d4);
        dos.add(d5);

        arc.add(tres);
        tres.add(e1);
        tres.add(e2);
        tres.add(e3);
        tres.add(e4);

        arc.add(cuatro);
        cuatro.add(r1);
        cuatro.add(r2);
        cuatro.add(r3);
        cuatro.add(r4);

        arc.add(cinco);
        cinco.add(x1);
        cinco.add(x2);

        arc.add(seis);
        seis.add(s1);
        seis.add(s2);

        arc.add(siete);
        siete.add(rgb1);
        siete.add(rgb2);
        siete.add(rgb3);

        arc.add(ocho);
        ocho.add(a1);

        contents.add(imagen, BorderLayout.CENTER);
        setSize(500, 400);
        setVisible(true);

    }

    // Funcio para Abrir Imagen
    public byte[] AbrirImagen(File archivo) {
        byte[] bytesImg = new byte[1000000];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        } catch (Exception e) {
        }
        return bytesImg;
    }

    // Funcion para Guardar Imagen
    public String GuardarImagen(File archiov, byte[] bytesImg) {
        String respuesta = null;
        try {
            salida = new FileOutputStream(archiov);
            salida.write(bytesImg);
            respuesta = "Se guardo con exito la imagen";
        } catch (Exception e) {
        }
        return respuesta;
    }

    // Negativo
    public void Negativo() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = 255 - c.getRed();
                int g = 255 - c.getGreen();
                int b = 255 - c.getBlue();
                c = new Color(r, g, b);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Grises
    public void Grises() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                c = new Color(prom, prom, prom);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Hacer mas claro una imagen
    public void powImage() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = (int) (255.0 * (Math.pow(Double.valueOf(c.getRed()) / 255.0, 0.9)));
                int g = (int) (255.0 * (Math.pow(Double.valueOf(c.getGreen()) / 255.0, 0.9)));
                int b = (int) (255.0 * (Math.pow(Double.valueOf(c.getBlue()) / 255.0, 0.9)));
                c = new Color(r, g, b);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Hacer mas oscuro una imagen
    public void Oscurecer() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = (int) (255.0 * (Math.pow(Double.valueOf(c.getRed()) / 255.0, 1.1)));
                int g = (int) (255.0 * (Math.pow(Double.valueOf(c.getGreen()) / 255.0, 1.1)));
                int b = (int) (255.0 * (Math.pow(Double.valueOf(c.getBlue()) / 255.0, 1.1)));
                c = new Color(r, g, b);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Binarizacion
    public void Binarizacion() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                if (prom > 127) {
                    c = new Color(255, 255, 255);
                } else {
                    c = new Color(0, 0, 0);
                }
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Laplace
    public void Laplace() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        int[][] matriz = new int[ancho][alto];
        int[][] matriz2 = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                matriz[i][j] = prom;
            }
        }
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int prom = matriz[i][j] * 8 - matriz[i - 1][j] - matriz[i + 1][j] - matriz[i][j - 1] - matriz[i][j + 1]
                        - matriz[i - 1][j - 1] - matriz[i + 1][j + 1] - matriz[i - 1][j + 1] - matriz[i + 1][j - 1];
                if (prom > 255) {
                    prom = 255;
                }
                if (prom < 0) {
                    prom = 0;
                }
                matriz2[i][j] = prom;
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(matriz2[i][j], matriz2[i][j], matriz2[i][j]);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Prewit
    private void Prewit() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        int[][] matriz = new int[ancho][alto];
        int[][] matriz2 = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                matriz[i][j] = prom;
            }
        }
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int prom = matriz[i][j] * 9 - matriz[i - 1][j] - matriz[i + 1][j] - matriz[i][j - 1] - matriz[i][j + 1]
                        - matriz[i - 1][j - 1] - matriz[i + 1][j + 1] - matriz[i - 1][j + 1] - matriz[i + 1][j - 1];
                if (prom > 255) {
                    prom = 255;
                }
                if (prom < 0) {
                    prom = 0;
                }
                matriz2[i][j] = prom;
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(matriz2[i][j], matriz2[i][j], matriz2[i][j]);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Sobel
    private void Sobel() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        int[][] matriz = new int[ancho][alto];
        int[][] matriz2 = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                matriz[i][j] = prom;
            }
        }
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int prom = matriz[i - 1][j - 1] + 2 * matriz[i - 1][j] + matriz[i - 1][j + 1] - matriz[i + 1][j - 1]
                        - 2 * matriz[i + 1][j] - matriz[i + 1][j + 1];
                if (prom > 255) {
                    prom = 255;
                }
                if (prom < 0) {
                    prom = 0;
                }
                matriz2[i][j] = prom;
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(matriz2[i][j], matriz2[i][j], matriz2[i][j]);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Roberts
    private void Roberts() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        int[][] matriz = new int[ancho][alto];
        int[][] matriz2 = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int prom = (r + g + b) / 3;
                matriz[i][j] = prom;
            }
        }
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int prom = matriz[i - 1][j - 1] - matriz[i + 1][j + 1];
                if (prom > 255) {
                    prom = 255;
                }
                if (prom < 0) {
                    prom = 0;
                }
                matriz2[i][j] = prom;
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(matriz2[i][j], matriz2[i][j], matriz2[i][j]);
                buff.setRGB(i, j, c.getRGB());
            }
        }
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Rotar a la derecha
    private void RotarDer() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                buff2.setRGB(j, ancho - i - 1, c.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion Rotar a la izquierda
    private void RotarIzq() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                buff2.setRGB(alto - j - 1, i, c.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para Rotar por cualquier angulo (en este caso 45°) CHECAR
    private void Rotar(int angulo) {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int x = (int) (i * Math.cos(Math.toRadians(angulo)) - j * Math.sin(Math.toRadians(angulo)));
                int y = (int) (i * Math.sin(Math.toRadians(angulo)) + j * Math.cos(Math.toRadians(angulo)));
                if (x >= 0 && x < ancho && y >= 0 && y < alto) {
                    buff2.setRGB(x, y, c.getRGB());
                }
            }
        }

        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para poner modo espejo en la imagen
    private void Espejo() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                buff2.setRGB(ancho - i - 1, j, c.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para hacer mas pequña la imagen
    private void Mpq() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho / 2, alto / 2, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i += 2) {
            for (int j = 0; j < alto; j += 2) {
                Color c = new Color(buff.getRGB(i, j));
                buff2.setRGB(i / 2, j / 2, c.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para hacer mas grande la imagen
    private void Mgd() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho * 2, alto * 2, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                buff2.setRGB(i * 2, j * 2, c.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para desenfocar la imagen
    private void Desenfocar() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int r2 = 0;
                int g2 = 0;
                int b2 = 0;
                int cont = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i + k >= 0 && i + k < ancho && j + l >= 0 && j + l < alto) {
                            Color c2 = new Color(buff.getRGB(i + k, j + l));
                            r2 += c2.getRed();
                            g2 += c2.getGreen();
                            b2 += c2.getBlue();
                            cont++;
                        }
                    }
                }
                r2 /= cont;
                g2 /= cont;
                b2 /= cont;
                Color c2 = new Color(r2, g2, b2);
                buff2.setRGB(i, j, c2.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para hacer el efecto de mosaico
    private void Mosaico() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i += 10) {
            for (int j = 0; j < alto; j += 10) {
                int r2 = 0;
                int g2 = 0;
                int b2 = 0;
                int cont = 0;
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if (i + k >= 0 && i + k < ancho && j + l >= 0 && j + l < alto) {
                            Color c2 = new Color(buff.getRGB(i + k, j + l));
                            r2 += c2.getRed();
                            g2 += c2.getGreen();
                            b2 += c2.getBlue();
                            cont++;
                        }
                    }
                }
                r2 /= cont;
                g2 /= cont;
                b2 /= cont;
                Color c2 = new Color(r2, g2, b2);
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if (i + k >= 0 && i + k < ancho && j + l >= 0 && j + l < alto) {
                            buff2.setRGB(i + k, j + l, c2.getRGB());
                        }
                    }
                }
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para mostrar la escala de verdes
    private void EscalaVerdes() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int r2 = 0;
                int g2 = 0;
                int b2 = 0;
                if (g > r && g > b) {
                    r2 = r;
                    g2 = g;
                    b2 = b;
                } else {
                    r2 = 0;
                    g2 = 0;
                    b2 = 0;
                }
                Color c2 = new Color(r2, g2, b2);
                buff2.setRGB(i, j, c2.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para mostrar la escala de azules
    private void EscalaAzules() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int r2 = 0;
                int g2 = 0;
                int b2 = 0;
                if (b > r && b > g) {
                    r2 = r;
                    g2 = g;
                    b2 = b;
                } else {
                    r2 = 0;
                    g2 = 0;
                    b2 = 0;
                }
                Color c2 = new Color(r2, g2, b2);
                buff2.setRGB(i, j, c2.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    // Funcion para mostrar la escala de rojos
    private void EscalaRojos() {
        int ancho = buff.getWidth();
        int alto = buff.getHeight();
        BufferedImage buff2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(buff.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int r2 = 0;
                int g2 = 0;
                int b2 = 0;
                if (r > g && r > b) {
                    r2 = r;
                    g2 = g;
                    b2 = b;
                } else {
                    r2 = 0;
                    g2 = 0;
                    b2 = 0;
                }
                Color c2 = new Color(r2, g2, b2);
                buff2.setRGB(i, j, c2.getRGB());
            }
        }
        buff = buff2;
        ImageIcon icon = new ImageIcon(buff);
        imagen.setIcon(icon);
    }

    private class PHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == u1) { // Abrir imagen
                if (seleccionado.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
                    archivo = seleccionado.getSelectedFile();
                    if (archivo.canRead()) {
                        if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png")
                                || archivo.getName().endsWith("jpng") || archivo.getName().endsWith("jpeg")) {
                            try {
                                buff = ImageIO.read(archivo);
                            } catch (FileNotFoundException ex) {
                                System.out.println("No se encontro el archivo");
                            } catch (IOException ex) {
                                System.out.println("Error al leer el archivo");
                            }
                            bytesImg = AbrirImagen(archivo);
                            imagen.setIcon(new ImageIcon(bytesImg));
                            setSize(buff.getWidth(), buff.getHeight() + 60);
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de imagen");
                        }
                    }
                }
            } else if (e.getSource() == u2) { // Abrir original
                imagen.setIcon(new ImageIcon(bytesImg));
                try {
                    buff = ImageIO.read(archivo);
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found Error");
                } catch (IOException ex) {
                    System.out.println("IO Error");
                }
                setSize(buff.getWidth(), buff.getHeight() + 60);
            } else if (e.getSource() == u3) { // Guardar imagen modificada
                seleccionado.setApproveButtonText("Guardar");
                if (seleccionado.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        archivo = seleccionado.getSelectedFile();
                        String formato = archivo.getName().split("\\.")[archivo.getName().split("\\.").length - 1];
                        ImageIO.write(buff, formato, archivo);
                    } catch (IOException ex) {
                        System.out.println("Error al guardar la imagen");
                    }
                }
            } else if (e.getSource() == u4) { // Salir
                System.exit(0);
            } else if (e.getSource() == d1) { // Negativo
                Negativo();
            } else if (e.getSource() == d2) { // Grises
                Grises();
            } else if (e.getSource() == d3) { // Brillantar
                powImage();
            } else if (e.getSource() == d4) { // Se modifica la imagen para que sea mas oscura
                Oscurecer();
            } else if (e.getSource() == d5) { // Se modifica la imagen para que sea binarizada
                Binarizacion();
            } else if (e.getSource() == e1) { // Aplicar laplace en la imagen
                Laplace();
            } else if (e.getSource() == e2) { // Aplicar sobel en la imagen
                Sobel();
            } else if (e.getSource() == e3) { // Aplicar Roberts a la imagen
                Roberts();
            } else if (e.getSource() == e4) { // Aplicar Prewitt a la imagen
                Prewit();
            } else if (e.getSource() == r1) { // Rotar a la derecha
                RotarDer();
            } else if (e.getSource() == r2) { // Rotar a la izquierda
                RotarIzq();
            } else if (e.getSource() == r3) { // Rotar por cualquier angulo
                int angulo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el angulo a rotar"));
                Rotar(angulo);
            } else if (e.getSource() == r4) { // Espejo
                Espejo();
            } else if (e.getSource() == x1) { // Hacer mas pequña la imagen
                Mpq();
            } else if (e.getSource() == x2) { // Hacer mas grande la imagen
                Mgd();
            } else if (e.getSource() == s1) { // Hacer el Desenfoque
                Desenfocar();
            } else if (e.getSource() == s2) { // Hacer el efecto mosaico
                Mosaico();
            } else if (e.getSource() == rgb1) { // Muestra en la imagen los pixeles rojos
                EscalaRojos();
            } else if (e.getSource() == rgb2) { // Muestra en la imagen los pixeles verdes
                EscalaVerdes();
            } else if (e.getSource() == rgb3) { // Muestra en la imagen los pixeles azules
                EscalaAzules();
            } else if (e.getSource() == a1) { // Mostrar informacion del proyecto
                JOptionPane.showMessageDialog(null, "Hecho por Diego Alberto Aranda Gonzalez\n"
                        + "Universidad Autonoma de Aguascalientes\n" + "Materia de Lenguajes de Programacion III\n");
            }
        }
    }

    public static void main(String args[]) {
        CheemsPhotoShop bm = new CheemsPhotoShop();
        bm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}