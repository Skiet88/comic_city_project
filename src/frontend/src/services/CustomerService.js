class CustomerService {
    constructor() {
    this.apiUrl = '/api/comiccity/Customer';
}

    async request(url, options) {
        try {
            const response = await fetch(url, options);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.statusText}`);
            }
            return options.method === 'DELETE' ? response.text() : response.json();
        } catch (error) {
            console.error('Fetch error:', error);
            throw error;
        }
    }

    async createCustomer(customer) {
        const url = `${this.apiUrl}/create`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(customer),
        });
    }


    async fetchCustomerByEmail(email) {
        const url = `${this.apiUrl}/getByEmail/${email}`;
        return await this.request(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
    }

}
export default new CustomerService();