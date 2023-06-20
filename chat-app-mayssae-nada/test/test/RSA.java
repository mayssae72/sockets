/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author mayssae
 */
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger n, e, d;

    public void generer_cles() {
        Random rnd = new Random();
        // Generer deux nombres premiers p et q
        BigInteger p, q;
        p = BigInteger.probablePrime(100, rnd);
        q = BigInteger.probablePrime(50, rnd);
        // calculer n
        n = p.multiply(q);
        // Calculer fi(n)
        BigInteger f;
        f = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
        // calculer e
        do {
            e = new BigInteger(30, rnd);
        } while (e.gcd(f).compareTo(BigInteger.ONE) != 0);
        // calculer d
        d = e.modInverse(f);
    }

    public RSA() {
    }

    public RSA(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public RSA(int id, BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public String chiffrer(String txt) {
        char[] toChar = txt.toCharArray();
        String txt_ch = "";
        BigInteger m;
        for (int i = 0; i < toChar.length; i++) {
            m = BigInteger.valueOf((int) toChar[i]);
            txt_ch += m.modPow(e, n).toString() + ",";
        }
        return txt_ch;
    }

    public String dechiffrer(String txt_dec) {
        String[] c = txt_dec.split(",");
        BigInteger[] t = new BigInteger[c.length];
        for (int i = 0; i < c.length; i++) {
            t[i] = new BigInteger(c[i]);
        }
        String s = "";
        BigInteger m;
        for (int i = 0; i < t.length; i++) {
            m = t[i].modPow(d, n);
            s += (char) m.intValue();
        }
        return s;
    }
}
