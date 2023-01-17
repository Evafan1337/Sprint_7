package data;

public class GetOrdersResponse {

    private GetOrdersResponseOrders orders;

    private GetOrdersResponsePageInfo pageInfo;

    private GetOrdersResponseAvaliableStations avaliableStations;

    public GetOrdersResponseOrders getOrders() {
        return orders;
    }

    public void setOrders(GetOrdersResponseOrders orders) {
        this.orders = orders;
    }

    public GetOrdersResponsePageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(GetOrdersResponsePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public GetOrdersResponseAvaliableStations getAvaliableStations() {
        return avaliableStations;
    }

    public void setAvaliableStations(GetOrdersResponseAvaliableStations avaliableStations) {
        this.avaliableStations = avaliableStations;
    }
}
