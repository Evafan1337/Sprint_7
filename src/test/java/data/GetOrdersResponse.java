package data;

import java.util.List;

public class GetOrdersResponse {

    private List<GetOrdersResponseOrdersElem> orders;
    private List<GetOrdersResponseAvailableStationsElem> availableStations;

    public List<GetOrdersResponseOrdersElem> getOrders() {
        return orders;
    }

    public void setOrders(List<GetOrdersResponseOrdersElem> orders) {
        this.orders = orders;
    }

    public List<GetOrdersResponseAvailableStationsElem> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<GetOrdersResponseAvailableStationsElem> availableStations) {
        this.availableStations = availableStations;
    }

    private GetOrdersResponsePageInfo pageInfo;

    //private GetOrdersResponseAvaliableStations avaliableStations;

//    public GetOrdersResponseOrders getOrders() {
//        return orders;
//    }
//
//    public void setOrders(GetOrdersResponseOrders orders) {
//        this.orders = orders;
//    }
//
    public GetOrdersResponsePageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(GetOrdersResponsePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
//
//    public GetOrdersResponseAvaliableStations getAvaliableStations() {
//        return avaliableStations;
//    }
//
//    public void setAvaliableStations(GetOrdersResponseAvaliableStations avaliableStations) {
//        this.avaliableStations = avaliableStations;
//    }
}
