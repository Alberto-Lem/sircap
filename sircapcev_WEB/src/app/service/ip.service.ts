// ip-service.ts
import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class IpService {
  private readonly IP_API_URL = 'https://api.ipify.org?format=json';

  getIpAddress(): Promise<string> {
    return axios.get(this.IP_API_URL).then(response => response.data.ip);
  }
}
