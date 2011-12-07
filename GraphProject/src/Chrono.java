public final class Chrono {
	
    private long begin;
    private long end;
    
    public boolean status;
    
    public Chrono() {
    	this.status = false;
    	this.begin = 0;
    	this.end = 0;
    }
    
    public void start() {
    	reset();
        begin = System.currentTimeMillis(); 
        // Timestamp du systeme.
    }
     
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public void reset() {
    	end = 0;
    	begin = 0;
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