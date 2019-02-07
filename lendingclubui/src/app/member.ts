export interface Member {
    id: string,
    loadAmnt: number,
    fundedAmntInv: number,
    term: string,
    intRate: number,
    installment: number,
    grade: string,
    empTitle: string,
    empLength: string,
    homeOwnership: string,
    annualInc: number,
    verificationStatus: string,
    issueDate: string,
    loadStatus: string,
    description: string,
    purpose: string,
    title: string,
    addrState: string,
    lastPymntDate: string,
    lastPymntAmnt: number

}

export interface MemberPaginated extends Paginated<Member>{
}

export interface Paginated<T>{
    totalRecords:number;
	pageSize:number;
	currentPage:number;
	contents:Array<T>;
}