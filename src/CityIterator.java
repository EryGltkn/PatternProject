public interface CityIterator {
    boolean hasNext();
    City next();
    void reset();
    City[] getCityList();
    String[] getCityListInString();
}
