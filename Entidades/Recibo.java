package Entidades;

class Recibo {
    private float total;
    private boolean pagado = false;

    public void setTotal(float total){
        this.total = total;
    }

    public float getTotal(){
        return total;
    }

    public boolean isPaid(){
        return pagado;
    }
    
    public pagar(){
        pagado = true;
    }
}