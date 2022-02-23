package co.com.poli.taller.service;

import co.com.poli.taller.entity.Backlog;
import co.com.poli.taller.repository.BacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogServiceImple implements BacklogService{

    @Autowired
    private BacklogRepository backlogRepository;

    @Override
    public Backlog createBacklog(Backlog backlog) {
        //sino existe el identifier del project
        if(findByProjectIdentifier(backlog.getProjectIdentifier()).isEmpty()){
            return null;
        }
        return backlogRepository.save(backlog);
    }

    @Override
    public Backlog findBacklogById(Long id) {
        return backlogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Backlog> findAllBacklog() {
        return backlogRepository.findAll();
    }

    @Override
    public Backlog updateBacklog(Backlog backlog, Long id) {
        if(findBacklogById(id) != null){
            Backlog backlog1 = findBacklogById(id);
            backlog1.setProjectIdentifier(backlog.getProjectIdentifier());
            return backlogRepository.save(backlog1);
        }
        return null;
    }

    @Override
    public Backlog deleteBacklog(Long id) {
        Backlog backlog = findBacklogById(id);
        if(backlog != null){
            backlogRepository.deleteById(id);
            return backlog;
        }
        return null;
    }

    @Override
    public List<Backlog> findByProjectIdentifier(String identifier) {
        return backlogRepository.findByProjectIdentifier(identifier);
    }


}
