import { Price } from "./price";

export interface CartItem {
    productId:string,
    image:string,
    price:Price,
    quantity:number
}
