public final class Chrono {
	
    private long begin;
    private long end;
 
    public void start(){
        begin = System.currentTimeMillis(); 
        // Timestamp du systeme.
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return getMilliseconds();
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public double getSeconds() {
        return getMilliseconds() / 1000.0;
    }
 
    public double getMinutes() {
        return getMilliseconds() / 60000.0;
    }
 
    // Si tout va bien, ne devrait pas servir !
    public double getHours() {
        return getMilliseconds() / 3600000.0;
    }
    
}