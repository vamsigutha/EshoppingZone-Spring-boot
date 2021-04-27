export interface Price {
    symbol:string,
    currency:string,
    current_price:number,
    discounted:boolean,
    before_price:number,
    savings_amount:number,
    savings_percent:number
}
