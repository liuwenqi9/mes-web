<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
        <div class="row">
        <div class="col-xs-12">
        <div class="box">
        <div class="box-header">
            <h3 class="box-title">用户管理</h3>
        </div><!-- /.box-header -->
        <div class="box-body table-responsive">
        <table id="example2" class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>用户名</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>张三</td>
            <td> 13818888811</td>
            <td>123@139.com</td>
            <td>
                <div class="">
                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-backdrop="static" data-target="#exampleModal" data-whatever="张三">编辑</button>
                    <button type="button" class="btn btn-danger btn-sm">删除</button>
                </div>
            </td>
        </tr>
        <tr>
            <td>李四</td>
            <td>13811199911</td>
            <td>aassdd@github.com</td>
            <td>
                <div class="">
                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-backdrop="static" data-target="#exampleModal" data-whatever="李四">编辑</button>
                    <button type="button" class="btn btn-danger btn-sm">删除</button>
                </div>
            </td>
        </tr>

        </tbody>
        </table>
        </div><!-- /.box-body -->
        </div><!-- /.box -->

        </div><!-- /.col -->
        </div><!-- /.row -->
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->