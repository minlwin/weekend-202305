var Container = /** @class */ (function () {
    function Container() {
        this.list = [];
    }
    Container.prototype.add = function (data) {
        this.list.push(data);
    };
    Container.prototype.get = function (index) {
        return this.list[index];
    };
    return Container;
}());
