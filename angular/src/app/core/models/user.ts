import { Address } from "./address";

export interface User {
    id:string,
    username:string,
    image:string,
    email:string,
    mobileNumber:number,
    dateOfBirth:string,
    gender:string,
    addressList:Array<Address>
}
