package domain.entity;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 22, 2017
 */
public class Odetail {

    private int ono;
    private int pno;
    private int qty;

    public Odetail(int ono, int pno, int qty) {
        this.ono = ono;
        this.pno = pno;
        this.qty = qty;
    }

    public Odetail() {
    }

    public int getOno() {
        return ono;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Odetail{" + "ono=" + ono + ", pno=" + pno + ", qty=" + qty + '}';
    }
    
}
