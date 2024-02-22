import { Injectable, NestInterceptor, ExecutionContext, CallHandler, HttpException, HttpStatus, ValidationError } from "@nestjs/common";
import e from "express";
import { Observable, catchError, map, throwError } from "rxjs";

@Injectable()
export class TransformResponseInterceptor<T> implements NestInterceptor<T, any> {
  intercept(_context: ExecutionContext, next: CallHandler): Observable<any> {
    return next
      .handle()
      .pipe(
        map(data => ({
          isSuccess: true,
          message: '',
          data: data,
        })),
        catchError(error => {
          try {
            if (error?.response?.message instanceof Array) {
              return throwError(() =>
                new HttpException({
                  isSuccess: false,
                  message: error.response.message.join(', '),
                  data: [],
                }, HttpStatus.OK)
              );
            } else {
              return throwError(() =>
                new HttpException({
                  isSuccess: false,
                  message: error.message || 'Error interno del servidor',
                  data: [],
                }, HttpStatus.OK)
              );
            }
          } catch (err) {
            return throwError(() =>
              new HttpException({
                isSuccess: false,
                message: err.message || 'Error interno del servidor',
                data: [],
              }, HttpStatus.OK)
            );
          }
        }),
      )
  }
}
