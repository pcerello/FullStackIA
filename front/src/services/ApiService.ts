// Class d'appel au backend

class ApiService {
    private baseURL: string;

    constructor(baseURL: string) {
        this.baseURL = baseURL;
    }

    async get(endpoint: string): Promise<any> {
        const response = await fetch(`${this.baseURL}/${endpoint}`);
        if (!response.ok) {
            throw new Error(`Erreur ${response.status}: ${response.statusText}`);
        }
        return await response.json();
    }

    async post(endpoint: string, data: any): Promise<any> {
        console.log(JSON.stringify(data))
        const response = await fetch(`${this.baseURL}/${endpoint}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ question: data }),
        });
        if (!response.ok) {
            throw new Error(`Erreur ${response.status}: ${response.statusText}`);
        }
        return await response.json();
    }
}

export default new ApiService("http://localhost:8083");
