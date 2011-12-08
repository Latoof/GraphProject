import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public final class Chrono {
	
    private long begin;
    private long end;
    ThreadMXBean mx = ManagementFactory.getThreadMXBean();
    
    public boolean status;
    
    public Chrono() {
    	this.status = false;
    	this.begin = 0;
    	this.end = 0;
    }
    
    public void start() {
    	reset();
        begin = mx.getCurrentThreadCpuTime();
        
        // Timestamp du systeme.
    }
     
    public void stop(){
        end = mx.getCurrentThreadCpuTime();
    }
 
    public void reset() {
    	end = 0;
    	begin = 0;
    }
    
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 

    
}