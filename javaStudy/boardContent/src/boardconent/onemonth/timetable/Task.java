package boardconent.onemonth.timetable;



public class Task {
    public int getRequiredWorkMinutes() {
        return requiredWorkMinutes;
    }


    public Task(int requiredWorkMinutes) {

        this.requiredWorkMinutes = requiredWorkMinutes;
    }

    private final int requiredWorkMinutes;


}


