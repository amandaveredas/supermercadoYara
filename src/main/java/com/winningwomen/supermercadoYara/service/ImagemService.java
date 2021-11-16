package com.winningwomen.supermercadoYara.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImagemService {

    @Value("${application.bucket.name}")
    private String bucketName;


    @Autowired
    private AmazonS3 s3Client;

    public String salvarImagem(MultipartFile arquivo){
        File arquivoFinal = converteMultipartFileParaFile(arquivo);
        String nomeArquivo = System.currentTimeMillis()+"_"+arquivo.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, nomeArquivo,arquivoFinal ));
        arquivoFinal.delete();
        String region = s3Client.getRegionName();
        String url = "https://"+bucketName+".s3."+region+".amazonaws.com/"+nomeArquivo;
        return url;
    }

    public byte[] exibirImagem(String nomeArquivo){
        S3Object s3Object = s3Client.getObject(bucketName, nomeArquivo);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        try {
           byte[] bytesImagem = IOUtils.toByteArray(s3ObjectInputStream);
           return bytesImagem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(String urlImagem){
        String[] urlPicada = urlImagem.split("/");
        String nomeArquivo = urlPicada[urlPicada.length - 1];
        s3Client.deleteObject(bucketName,nomeArquivo);
    }

    private File converteMultipartFileParaFile(MultipartFile arquivo){
        File arquivoConvertido = new File(arquivo.getOriginalFilename());

        try {
            FileOutputStream fos = new FileOutputStream(arquivoConvertido);
            fos.write(arquivo.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  arquivoConvertido;

    }
}
