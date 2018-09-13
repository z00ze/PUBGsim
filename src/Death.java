public class Death {

    float x;
    float y;

    Death(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    @Override
    public String toString(){
        return Float.toString(this.x) + " " + Float.toString(this.y);
    }
}
