

class Users {
    String acno;
    String pass;

    public Users(String acno, String pass, String voterid, String keys, int balance) {
        this.acno = acno;
        this.pass = pass;
        this.voterid = voterid;
        this.keys = keys;
        this.balance = balance;
    }

    public Users() {
    }

    String voterid;
    String keys;
    int balance;

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
