import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AuthenticateService } from './authentication.service';
import { Observable } from 'rxjs';

const testConfig = {

    addUser: {
      positive: {
        firstName: 'test',
        lastName: 'testLast',
        userId: 'testUser',
        password: 'testPass'
      }
    },

    loginUser: {
      positive: {
        userId: 'testUser',
        password: 'testPass'
      }
    }
}

describe('AuthenticateService', () => {
  let authService: AuthenticateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [AuthenticateService]
    });
    authService = TestBed.get(AuthenticateService);
  });

  it('should be created Authenticate Service', 
   inject([AuthenticateService], (service: AuthenticateService) => {
    expect(service).toBeTruthy();
  }));

  it('should register user data', fakeAsync(() => {
    let data = testConfig.addUser.positive;
    inject([AuthenticateService, HttpTestingController], (backend: HttpTestingController) => {
      const mockReq = backend.expectOne(authService.springEndPoint);
      expect(mockReq.request.url).toEqual(authService.springEndPoint, 'request url should match with json server api url');
      expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
      mockReq.flush(data);
      backend.verify();
    });
    authService.registerUser(data).subscribe((res: any) => {
      expect(res).toBeDefined();
      expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should login user', fakeAsync(() => {
    let userData = testConfig.loginUser.positive;
    inject([AuthenticateService, HttpTestingController], (backend: HttpTestingController) => {
      const mockReq = backend.expectOne(authService.springEndPoint);
      expect(mockReq.request.url).toEqual(authService.springEndPoint, 'request url should match with json server api url');
      expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
      mockReq.flush(userData);
      backend.verify();
    });
    authService.loginUser(userData).subscribe((res: any) => {
      expect(res).toBeDefined();
      expect(res._body).toBe(userData, 'data should be same');
    });
  }));
});
