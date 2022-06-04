import java.util.Scanner;
class pembeli{
    String namaPembeli;
    String noHp;
    int noAntrian;
    
    pembeli(int noAntrian, String namaPem, String noHp){
        this.namaPembeli = namaPembeli;
        this.noHp = noHp;
        this.noAntrian = noAntrian;
    }
}

class pesanan{
    int kodePesanan;
    String namaPesanan;
    int harga;

    pesanan(int kodePesanan, String namaPesanan, int harga){
        this.kodePesanan = kodePesanan;
        this.namaPesanan = namaPesanan;
        this.harga = harga;
    }
}

class Node{
    Node prev, next;
    pembeli pmb;
    pesanan psn;
    

    Node(Node prev, pembeli pmb, Node next) {
        this.prev = prev;
        this.pmb = pmb;
        this.next = next;
    }
    Node(Node prev, pesanan psn, Node next){
        this.prev=prev;
        this.psn=psn;
        this.next=next;
    
    }
}



class doubleLinkedList{
    Node head;
    
    int size; 

    doubleLinkedList(){
        head = null;
        size=0;
    }
    boolean isEmpty(){
        return head==null;
    }

    void addFirst(pembeli pmb){
        if (isEmpty()) {
            head = new Node(null, pmb, null);
        } else {
            Node newNode = new Node(null, pmb, head);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    void addFirst2(pesanan psn){
        if (isEmpty()) {
            head = new Node(null, psn, null);
        } else {
            Node newNode = new Node(null, psn, head);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(pembeli pmb){
        if (isEmpty()) {
            addFirst(pmb);
        } else {
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            Node newNode = new Node(current, pmb, null);
            current.next = newNode;
            size++;
        }
    }
    public void addLast2(pesanan psn){
        if (isEmpty()) {
            addFirst2(psn);
        } else {
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            Node newNode = new Node(current, psn, null);
            current.next = newNode;
            size++;
        }
    }

    void print() {
        if (!isEmpty()) {
            Node tmp = head;
            while (tmp != null) {
                System.out.println("Nomor Antrian : " + tmp.pmb.noAntrian);
                System.out.println("Nama Customer : " + tmp.pmb.namaPembeli);
                System.out.println("Nomor Hp : " + tmp.pmb.noHp);
                tmp = tmp.next;
            }
            System.out.println("\nTotal Antrian : " + size);
        } else {
            System.out.println("Linked Lists kosong");
        }
    }

    public void print2(){
        if(!isEmpty()){
            Node tmp = head;
            while (tmp!=null){
                System.out.println("|"+ tmp.psn.kodePesanan +"\t|"+ tmp.psn.namaPesanan+"\t|" + tmp.psn.harga);
                tmp=tmp.next;
            }
        }else{
            System.out.println("Linked Lists kosong");
        }
    }

    void removeFirst() throws Exception {
        if (isEmpty()) {
            System.out.println("Linked List masih kosong, tidak dapat dihapus");
        } else if (size == 1) {
            removeLast();
        } else {
            head = head.next;
            head.prev = null;
            size--;
        }
    }
    void removeLast() throws Exception {
        if (isEmpty()) {
            throw new Exception("Linked List masih kosong, tidak dapat dihapus!");
        } else if (head.next == null) {
            head = null;
            size--;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        System.out.println("Nomor Antrian : " + current.psn.kodePesanan);
        System.out.println("Nama Customer : " + current.psn.namaPesanan);
        System.out.println("Nama Customer : " + current.psn.harga);
        current.next = null;
        size--;
    }

    void hitungPendapatan() {
        int total = 0;
        Node current = head;
        while (current != null) {
            total = total + current.psn.harga;
            current = current.next;
        }
        System.out.println("\nTotal Pendapatan = " + total);
    }
}


public class Node_16 {
    public static void main(String [] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        
        doubleLinkedList db = new doubleLinkedList();
        int noAntrian, noPesanan, harga;
        String namaPembeli, namaPesanan;
        String noHp;
        for(;;){
            menu();
            System.out.print("Pilih (1-5): ");
            int pilih = sc.nextInt();
            if(pilih==1){
                System.out.println("Masukkan Data Pembeli");
                System.out.print("Nomor Antrian: ");
                noAntrian = sc.nextInt();
                System.out.print("Nama Customer: ");
                namaPembeli =sc.next();
                System.out.print("Nomor Hp: ");
                noHp= sc.next();
                pembeli pmb = new pembeli(noAntrian, namaPembeli, noHp);
                db.addLast(pmb);
            }else if(pilih==2){
                System.out.println();
                    System.out.println("------------------------------");
                    System.out.println("Daftar Antrian Rest Royal Delish");
                    System.out.println("------------------------------");
                    db.print();
            }else if(pilih==3){
                db.removeFirst();
                System.out.println("Trasnsaksi input pesanan");
                System.out.print("Nomor Pesanan: ");
                noPesanan = sc.nextInt();
                System.out.print("pesanan: ");
                namaPesanan =sc.next();
                System.out.print("harga: ");
                harga= sc.nextInt();
                pesanan psn = new pesanan(noPesanan, namaPesanan, harga);
                db.addLast2(psn);
                System.out.println("------------------------------");
                System.out.println("Daftar Antrian Rest Royal Delish");
                System.out.println("------------------------------");
                db.print2();
            }else if(pilih==4){
                break;
            }else if(pilih==5){
                db.hitungPendapatan();
                break;
            }else{
                break;

           }
        }
    }
    public static void menu(){
        System.out.println("==============================");
        System.out.println("Menu");
        System.out.println("==============================");
        System.out.println("1. Tambah Antrian");
        System.out.println("2. Cetak Antrian");
        System.out.println("3. Hapus Antrian");
        System.out.println("4. Laporan Pengurutan by nama");
        System.out.println("5. Hitung total pendapatan");
        System.out.println("6. Keluar");
        System.out.println("==============================");
    }

}
