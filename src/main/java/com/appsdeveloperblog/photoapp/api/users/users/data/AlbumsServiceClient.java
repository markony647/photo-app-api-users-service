package com.appsdeveloperblog.photoapp.api.users.users.data;

import com.appsdeveloperblog.photoapp.api.users.users.ui.model.AlbumResponseModel;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}

@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumsServiceClient> {
    @Override
    public AlbumsServiceClient create(Throwable throwable) {
        return new AlbumsServiceClientFallback(throwable);
    }
}

@Slf4j
class AlbumsServiceClientFallback implements AlbumsServiceClient {

    private final Throwable throwable;

    public AlbumsServiceClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        if (throwable instanceof FeignException && ((FeignException) throwable).status() == 404) {
            log.error("404 error took place when getAlbums was called with userId: {}", id);
            log.error("Error message: {}", throwable.getLocalizedMessage());
        } else {
            log.error("Other error took place: {}", throwable.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
}
